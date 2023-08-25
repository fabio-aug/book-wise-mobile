package br.edu.ifmg.bookwise.classes;

import java.util.Arrays;

import androidx.annotation.NonNull;

public class Gender {
    public Integer id;
    public String label;

    public Gender(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    @NonNull
    @Override
    public String toString() {
        return "-" + id + "-" + label;
    }

    public static Gender[] stringArrayToObjectArray(String str) {
        str = str.replace("[", "").replace("]", "");

        String[] arrString = str.split(", ");
        Gender[] arrGender = new Gender[arrString.length];

        for (int i = 0; i < arrString.length; i++) {
            String[] stringGender = arrString[i].split("-");
            arrGender[i] = new Gender(Integer.parseInt(stringGender[1]), stringGender[2]);
        }

        return arrGender;
    }

    public static String objectArrayToStringArray(Gender[] genders) {
        return Arrays.toString(genders);
    }

    public static String renderArrayObjects(Gender[] genders) {
        StringBuilder aux = new StringBuilder();

        for (int i = 0; i < genders.length; i++) {
            if (i == genders.length - 1) {
                aux.append(genders[i].label);
            } else {
                aux.append(genders[i].label).append(" - ");
            }
        }

        return aux.toString();
    }
}
