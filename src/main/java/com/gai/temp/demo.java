package com.gai.temp;

import java.util.regex.Pattern;

public class demo {
    public static void main(String[] args) {
        String sql = "select employee_id, max(cast(organization_id as integer)) \n" +
                "organization_id \n" +
                "from src_ht_hr_ims.ims_rt_employee_organization where state=1\n" +
                "and jobtitle <> '--' --and employee_id='50019940'\n" +
                "group by employee_id  ";
        Pattern p = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$|/\\*.*?\\*/|#.*?$|");
        String result = p.matcher(sql).replaceAll("$1");
        System.out.println(result);
    }
}
