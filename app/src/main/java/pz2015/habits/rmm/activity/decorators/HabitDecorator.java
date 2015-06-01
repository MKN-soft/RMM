package pz2015.habits.rmm.activity.decorators;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.List;

import pz2015.habits.rmm.model.HabitDefinition;

/**
 * Class used to mark on the calendar habits(red circle ).
 */
public class HabitDecorator implements DayViewDecorator {
    List<HabitDefinition> definedHabits;

    public HabitDecorator(List<HabitDefinition> habDefs) {
        definedHabits = habDefs;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        for (HabitDefinition habDef : definedHabits) {
            if (compare(habDef, calendarDay) == true) {
                if (!habDef.getDone()) {
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

    private boolean compare(HabitDefinition hd, CalendarDay cDay) {
        if (hd.getDay() == cDay.getDay()) {
            if (hd.getMonth() == cDay.getMonth()) {
                if (hd.getYear() == cDay.getYear()) {
                    return true;
                }
            }
        }


        //   Log.d("RMM", " " + hd.getDay() + " " + cDay.getDay() + " " + hd.getMonth() + " " + cDay.getMonth());
        return false;
    }
}
