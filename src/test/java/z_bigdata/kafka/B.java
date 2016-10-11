package z_bigdata.kafka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by GP39 on 2016/8/22.
 */
public class B {

    public static void main(String[] args) throws ParseException {
        String old_date="20160702";
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(old_date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        instance.add(Calendar.MONTH,1);
        System.out.println(instance.getTime());


    }


}
