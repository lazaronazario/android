package com.loremipsum.recifeguide.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BREVE DEUS VEM on 03/10/2016.
 */

public class FormatHelper {

    public static Timestamp converterHorarioToTimeStamp(String hhmm) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = formatter.parse(hhmm);
        Timestamp timestamp = new Timestamp(date.getTime());

        return timestamp;
    }

    public static String converterTimeStampToHorario(Timestamp ts) throws ParseException {
        String ret = "";
        if (ts != null) {

            DateFormat formatter = new SimpleDateFormat("HH:mm");
            ret = formatter.format(ts);
        }

        return ret;
    }
}
