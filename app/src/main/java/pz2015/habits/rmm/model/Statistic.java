package pz2015.habits.rmm.model;

import java.util.List;

/**
 * Created by ASUS on 2015-05-31.
 * Represents statistics. It is displayed when we view statistics (not implemented)
 */
public class Statistic {

    int bestScoreMatrix[];
    int averageLong;
    int bestScore;
    float successPercent;
    int habitDurations;
    int habitSize;

    String id;
    String date;
    String frequency;

    List<HabitDefinition> definedHabits;

    // For last added habit
    HabitDefinition lastHabit;

    public Statistic(Habit habit) {
        this.bestScoreMatrix = new int[255];
        for (int i = 0; i < this.bestScoreMatrix.length; i++)
            bestScoreMatrix[i] = 0;

        this.id = habit.getId();
        this.date = habit.getDate();
        this.frequency = habit.getFrequency();
        this.definedHabits = habit.getHabitDefinitions();

        if (this.definedHabits.size() > 0) {
            this.lastHabit = this.definedHabits.get(this.definedHabits.size() - 1);
            // Make statistics
            this.someThings();
        }
        else
            this.lastHabit = null;


    }

    public boolean emptyLastHabit() {
        if (this.lastHabit == null)
            return true;
        else
            return false;
    }

    private void someThings() {
        int k = 0;
        bestScore = 0;
        averageLong = 0;
        successPercent = 0;
        habitDurations = 0;
        int frequency = Integer.parseInt(this.frequency);

        boolean endOfDuration = false;

        // Inaczej nie można zbudować statystyk...
        if (frequency < this.definedHabits.size()) {

            for (int i = this.definedHabits.size() - 1; i >= 0; ) {
                HabitDefinition habitDefinition = this.definedHabits.get(i);

                if (habitDefinition.getDone()) {
                    // To znaczy że trafiliśmy na nowy ciąg
                    if (endOfDuration == true) {
                        k++;
                    }

                    bestScoreMatrix[k]++;
                    averageLong++;
                } else {
                    endOfDuration = true;
                    habitDurations++;
                }
                i = i - frequency;
            }

            bestScore = bestScoreMatrix[0];

            for (int i = 0; i < bestScoreMatrix.length; i++) {
                if (bestScore < bestScoreMatrix[i])
                    bestScore = bestScoreMatrix[i];
            }


            float size = (float) this.definedHabits.size();
            successPercent = (float) averageLong / size;
            successPercent = successPercent * 100;
            averageLong = averageLong / habitDurations;
            habitSize = this.definedHabits.size();

        }

    }

    public boolean getDone() { return this.lastHabit.getDone(); }

    public String getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public String getLastDate() { return this.lastHabit.getDay() + "." + this.lastHabit.getMonth() + "." + this.lastHabit.getYear(); }

    public int getBestScore() { return bestScore; }

    public int getAverageLong() { return averageLong; }

    public float getSuccessPercent() { return successPercent; }

    public int getHabitSize() { return habitSize; }
}
