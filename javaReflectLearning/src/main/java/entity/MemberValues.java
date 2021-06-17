package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 成员变量值
 */
public class MemberValues {
    MemberCategory memberCategory;
    /**
     * 如果MemberCategory为OBJECT或者ARRAY，则memberValues会继续映射其成员变量和类型
     */
    Map<String, MemberValues> memberValues;
    // 泛型存储类型（List，Map)
    MemberValues value;
    public boolean isFinalCategory(){
        if(memberCategory == MemberCategory.ARRAY || memberCategory == MemberCategory.OBJECT|| memberCategory == MemberCategory.MAP){
            return false;
        }
        return true;
    }

    public MemberValues(MemberCategory memberCategory){
        this.memberCategory = memberCategory;
        memberValues = null;
        value = null;
    }
    public MemberValues(MemberCategory memberCategory, MemberValues v){
        this.memberCategory = memberCategory;
        memberValues = null;
        value = v;
    }

    public void setValue(MemberValues v){
        value = v;
    }
    public void addMemberValue(String memberName, MemberValues memberValues){
        if(this.memberValues == null){
            this.memberValues = new HashMap<>(16);
        }
       this.memberValues.put(memberName, memberValues);
    }
}
