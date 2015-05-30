package pz2015.habits.rmm.activity;


import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

import pz2015.habits.rmm.R;

/**
 * Created by Natka on 2015-05-25.
 */
public class CalendarActivity extends Activity {
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        MaterialCalendarView calendar = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangedListener(new OnDateChangedListener() {
            @Override
            public void onDateChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {

            }
        });
    }

}


/*
*   xmlns:app="http://schemas.android.com/apk/res-auto"
        app:mcv_showOtherDates="boolean"
        app:mcv_arrowColor="color"
        app:mcv_selectionColor="color"
        app:mcv_headerTextAppearance="style"
        app:mcv_dateTextAppearance="style"
        app:mcv_weekDayTextAppearance="style"
        app:mcv_weekDayLabels="array"
        app:mcv_monthLabels="array"
        app:mcv_tileSize="dimension"
        app:mcv_firstDayOfWeek="enum"
* */