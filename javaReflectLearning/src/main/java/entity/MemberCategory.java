package entity;

import org.apache.commons.lang.StringUtils;

/**
 * 成员变量枚举类
 */
public enum MemberCategory {
    INTEGER("{Number}","Integer"),
    LONG("{Number}", "Long"),
    SHORT("{Number}", "Short"),
    FLOAT("{Number}","Float"),
    DOUBLE("{Number}", "Double"),
    BIGDECIMAL("{Number}","BigDecimal"),
    BOOLEAN("{Boolean}", "Boolean"),
    STRING("{String}","String"),
    // 可拆成员
    OBJECT("{Object}", "Object"),
    ARRAY("{Array}","Array"),
    MAP("{Object}","Map");
    String name;
    String typeName;
    MemberCategory(String name,String typeName){
        this.name = name;
        this.typeName = typeName;
    }
    public static MemberCategory getMemberCategoryByTypeName(String typeName){
        for(MemberCategory category : MemberCategory.values()){
            if(StringUtils.equals(typeName, category.getTypeName())){
                return category;
            }
        }
        if(StringUtils.contains(typeName, "Array"))
            return MemberCategory.ARRAY;
        if(StringUtils.contains(typeName, "Map"))
            return MemberCategory.MAP;
        return MemberCategory.OBJECT;
    }
    public static boolean isPrimitiveCategory(MemberCategory memberCategory){
        if(memberCategory == OBJECT || memberCategory == ARRAY || memberCategory==MAP){
            return false;
        }
        else return true;
    }
    public String getName(){
        return this.name;
    }
    public String getTypeName(){
        return this.typeName;
    }
}
