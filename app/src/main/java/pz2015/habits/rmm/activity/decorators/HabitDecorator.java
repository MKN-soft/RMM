package pz2015.habits.rmm.activity.decorators;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Date;
import java.util.List;

import pz2015.habits.rmm.model.HabitDefinition;

/**
 * Created by Natka on 2015-05-30.
 */
public class HabitDecorator implements DayViewDecorator {
    List<HabitDefinition> definedHabits;

    public HabitDecorator(List<HabitDefinition> habDefs){
        definedHabits = habDefs;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        for(HabitDefinition habDef : definedHabits){
            if(compare(habDef, calendarDay) == true){
                if( ! habDef.isDone()){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
       // dayViewFacade.addSpan(new StyleSpan(Typeface.BOLD));
       // dayViewFacade.addSpan(new RelativeSizeSpan(1.4f));

        dayViewFacade.addSpan(new DotSpan(5, Color.RED));
    }

    private boolean compare(HabitDefinition hd, CalendarDay cDay){
        if(hd.getDay() == cDay.getDay()){
            if(hd.getMonth() == cDay.getMonth()) {
                if(hd.getYear() == cDay.getYear() ){
                    return true;
                }
            }
       }



     //   Log.d("RMM", " " + hd.getDay() + " " + cDay.getDay() + " " + hd.getMonth() + " " + cDay.getMonth());
        return false;
    }
}
