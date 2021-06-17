package base;


import entity.MemberCategory;
import entity.MemberValues;
import org.apache.commons.lang.StringUtils;
import utils.StrUtils;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * @author long
 * @version 1.0
 * Load all member information of a class through reflection
 */
public class LoadClassUtils {

    /**
     * 通过递归查找所有成员变量信息
     * @param clz 需要获取的对象
     * @return 该类的所有成员变量信息
     */
    public static MemberValues getAllMember(Class clz) throws IllegalAccessException {
        MemberCategory thisCategory = getClassCategory(clz);
        if(MemberCategory.isPrimitiveCategory(thisCategory)){
            return new MemberValues(thisCategory);
        }
        else{
            //如果为类则反射其所有类型，如果为列表或map则获取其泛类的成员类型
            MemberValues memberValues = new MemberValues(thisCategory);
            if(thisCategory == MemberCategory.OBJECT){
                Field[] fields = clz.getDeclaredFields();
                for(Field field : fields){
                    // 剔除内部类，静态变量
                    if(Modifier.isStatic(field.getModifiers())|| StrUtils.equals(field.getName(),"this\\$[0-9]+"))
                        continue;
                    field.setAccessible(true);
                    Type fieldType = field.getType();
                    if(fieldType== List.class||fieldType == Map.class){
                        Type genericType = field.getGenericType();
                        if(genericType instanceof ParameterizedType){
                            ParameterizedType pt = (ParameterizedType) genericType;
                            //得到泛型里的class类型对象
                            if(fieldType == List.class){
                                Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];
                                MemberValues temp = new MemberValues(MemberCategory.ARRAY, getAllMember(genericClazz));
                                memberValues.addMemberValue(field.getName(),temp);
                            }
                            else{
                                Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[1];
                                MemberValues temp = new MemberValues(MemberCategory.MAP, getAllMember(genericClazz));
                                memberValues.addMemberValue(field.getName(),temp);
                            }
                        }
                    }
                    else{
                        memberValues.addMemberValue(field.getName(),getAllMember(field.getType()));
                    }
                }
                return memberValues;
            }
        }
        return null;
    }
    private static MemberCategory getClassCategory(Class clz){
        String[] spiltNames = clz.getTypeName().split("\\.");
        String typeName = spiltNames[spiltNames.length - 1];
        return MemberCategory.getMemberCategoryByTypeName(typeName);
    }
}
