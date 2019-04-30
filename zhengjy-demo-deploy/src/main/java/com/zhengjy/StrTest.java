package com.zhengjy;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by Jiyang.Zheng on 2019/2/19 8:58.
 */
public class StrTest {

    public static void main(String[] args) {
        String sql = "select * from dual where b=#{b} and  b=#{b} and   a=#{a}  and  c=#{c} and  d=#{d}";
        Map<String,String> map = Maps.newHashMap();
        map.put("a","1");
        map.put("d","2");


        System.out.println(sqlHandle( sql,map ));
    }


    public static String sqlHandle(String paramSql,Map<String,String> param ){
        StringBuilder retSql = new StringBuilder();
        String tempSql = paramSql;
        // search open token
        int start = paramSql.indexOf("#{");
        if (start == -1) {
            return paramSql;
        }
        while (start > -1) {
            int openTokenIndex = start+2;
            String sqlTemp = tempSql.substring(openTokenIndex,tempSql.length());
            int closeTokenIndex = sqlTemp.indexOf("}");
            //#{}值
            String value = tempSql.substring(openTokenIndex,openTokenIndex+closeTokenIndex);
            String mapVal = param.get(value);
            if (StringUtils.isEmpty(mapVal)){
                int indexStart = isWhere(tempSql) ? tempSql.indexOf("where") : tempSql.indexOf("and");
                int indexEnd = tempSql.indexOf("}")+1;
                String sqlStart = tempSql.substring(0,indexStart);
                String sqlEnd = tempSql.substring(indexEnd,tempSql.length());
                String appendSql = isWhere(tempSql) ? (sqlStart +" where 1=1 ") : sqlStart;
                retSql.append(appendSql);
                tempSql = sqlEnd;
            }else {
                int indexEnd = tempSql.indexOf("}")+1;
                String appendSql = tempSql.substring(0,indexEnd);
                retSql.append(appendSql);
                String sqlEnd = tempSql.substring(indexEnd,tempSql.length());
                tempSql = sqlEnd;
            }
            start = tempSql.indexOf("#{");
        }
        return retSql.toString();
    }

    private static boolean isWhere(String sql){
        return sql.indexOf("where") > -1;
    }
}
