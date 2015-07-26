package com.javiersantos.moticons.utils;

import android.content.Context;
import android.util.Log;

import com.javiersantos.moticons.Moticon;
import com.javiersantos.moticons.MoticonCategory;
import com.javiersantos.moticons.MoticonKeywords;
import com.javiersantos.moticons.MoticonsApplication;
import com.javiersantos.moticons.activities.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UtilsMoticons {
    private static AppPreferences appPreferences;

    public static List<Moticon> loadMoticons() {
        List<Moticon> moticons = new ArrayList<>();

        // FUNNY
        moticons.add(new Moticon("(●´∀｀●)", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("(｀・ω・´)”", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("(*´・ｖ・)", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("(⌒▽⌒)☆", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("⊂((・▽・))⊃", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("（・◇・）", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("（＾_＾）", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("∩( ・ω・)∩", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("＼（＾ ＾）／", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("(^～^)", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("(/^▽^)/", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY)), 5));
        moticons.add(new Moticon("(ﾉ´ｰ`)ﾉ", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("ヽ(´ー`)ﾉ", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY)), 5));
        moticons.add(new Moticon("（＾ω＾）", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("｡◕‿◕｡", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY)), 5));
        moticons.add(new Moticon("(︶ω︶)", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("ヽ(^。^)丿", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY)), 5));
        moticons.add(new Moticon("∩(︶▽︶)∩", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("(¬‿¬)", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("(•‿•)", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));
        moticons.add(new Moticon("( ͡° ͜ʖ ͡°)", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HAPPY))));

        moticons.add(new Moticon("( ˘ ³˘)❤", MoticonCategory.POSITIVE, new ArrayList<>(Arrays.asList(MoticonKeywords.LOVE, MoticonKeywords.KISSING)), 5));
        moticons.add(new Moticon("(・_・)❤(-_-)", MoticonCategory.POSITIVE, new ArrayList<>(Arrays.asList(MoticonKeywords.LOVE, MoticonKeywords.KISSING))));
        moticons.add(new Moticon("（´・｀ ）♡", MoticonCategory.POSITIVE, new ArrayList<>(Arrays.asList(MoticonKeywords.LOVE, MoticonKeywords.KISSING))));
        moticons.add(new Moticon("(´ε｀ )♡", MoticonCategory.POSITIVE, new ArrayList<>(Arrays.asList(MoticonKeywords.LOVE, MoticonKeywords.KISSING))));

        moticons.add(new Moticon("＼(^o^)／", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.EXCITED)), 10));

        moticons.add(new Moticon("(づ￣ ³￣)づ", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HUGGING))));
        moticons.add(new Moticon("(っ˘з(˘⌣˘ )", MoticonCategory.POSITIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.HUGGING))));

        // NEGATIVE
        moticons.add(new Moticon("(>_<)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("（＞д＜）", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("(¬д¬。)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("(¬､¬)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("（；¬＿¬)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("ヽ(●-`Д´-)ノ", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("(¬_¬)ﾉ", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("(」゜ロ゜)」", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("＼(｀0´)／", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY)), 5));
        moticons.add(new Moticon("(ノಠ益ಠ)ノ", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("ᕙ(⇀‸↼‶)ᕗ", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("( ಠ ಠ )", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY))));
        moticons.add(new Moticon("ಠ_ಠ", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.ANGRY)), 10));

        moticons.add(new Moticon("ಥ_ಥ", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.CRYING))));
        moticons.add(new Moticon("(¬_¬)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.SAD))));
        moticons.add(new Moticon("t(-_-t)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.SAD))));
        moticons.add(new Moticon("(҂⌣̀_⌣́)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.SAD))));

        moticons.add(new Moticon("(T＿T)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.SAD))));
        moticons.add(new Moticon("⊙︿⊙", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.SAD)), 5));
        moticons.add(new Moticon("o(╥﹏╥)o", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.CRYING))));
        moticons.add(new Moticon("(╯︵╰,)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.SAD))));
        moticons.add(new Moticon("(っ˘̩╭╮˘̩)っ", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.SAD))));

        moticons.add(new Moticon("(~_~;)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.WORRIED))));
        moticons.add(new Moticon("⊙﹏⊙", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.WORRIED))));
        moticons.add(new Moticon("(⊙…⊙ )", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.WORRIED)), 10));

        moticons.add(new Moticon("(✖╭╮✖)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.DEAD))));
        moticons.add(new Moticon("(*_*)", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.DEAD))));
        moticons.add(new Moticon("✖‿✖", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.DEAD))));
        moticons.add(new Moticon("╭( ✖_✖ )╮", MoticonCategory.NEGATIVE, new ArrayList<>(Collections.singletonList(MoticonKeywords.DEAD))));

        // FUNNY
        moticons.add(new Moticon("¯\\_(ツ)_/¯", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("ヽ（´ー｀）┌", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("ヽ( ´¬`)ノ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("┗┃・ ■ ・┃┛", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("ヽ（・＿・；)ノ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("ヽ(。_°)ノ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("(;´・`)>", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("┐(￣ヮ￣)┌", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER)), 10));
        moticons.add(new Moticon("╰(　´◔　ω　◔ `)", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));
        moticons.add(new Moticon("╮(╯▽╰)╭", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.WHATEVER))));

        moticons.add(new Moticon("(/・・)ノ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING)), 5));
        moticons.add(new Moticon("(o´･_･)っ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("(ﾉ･ｪ･)ﾉ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("(ﾉ*ﾟｰﾟ)ﾉ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("＼(ﾟｰﾟ＼)", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("♪(┌・。・)┌", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("〜(^∇^〜）", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING)), 5));
        moticons.add(new Moticon("ヽ(*ﾟｰﾟ*)ﾉ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("〜(￣△￣〜)", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("（〜^∇^)〜", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("(~‾▿‾)~", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING)), 5));
        moticons.add(new Moticon("(┌ﾟдﾟ)┌", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("(ﾉ･o･)ﾉ", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("┐(ﾟдﾟ┐)", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("└(^o^)┐", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING)), 10));
        moticons.add(new Moticon("ƪ(‾.‾“)┐", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));
        moticons.add(new Moticon("ƪ(˘⌣˘)┐", MoticonCategory.FUNNY, new ArrayList<>(Collections.singletonList(MoticonKeywords.DANCING))));


        retrieveInternalStats(moticons);
        checkTimes(moticons);
        //checkFavorites(moticons);
        checkUnlocked(moticons);

        return moticons;
    }

    private static void retrieveInternalStats(List<Moticon> moticonList) {
        // Retrieve if there are two equals IDs
        List<String> tempList = new ArrayList<>();
        for (Moticon moticon : moticonList) {
            tempList.add(moticon.getMoticon());
        }
        Collections.sort(tempList);
        for (int i = 0; i < tempList.size()-1; i++) {
            if (tempList.get(i).equals(tempList.get(i+1))) {
                Log.i("MOTICON DUPLICATED! -> ", tempList.get(i));
            }
        }

        // Retrieve total of Moticons moticoins
        Double moticoins = 0.;
        for (Moticon moticon : moticonList) {
            moticoins += moticon.getMoticoins();
        }
        Log.i("TOTAL MOTICOINS -> ", retrieveMoticoins(moticonList).toString());
    }

    private static void checkTimes(List<Moticon> moticonList) {
        appPreferences = MoticonsApplication.getAppPreferences();
        for (String string : appPreferences.getMoticonTimes()) {
            String[] split = string.split("##");
            if (split.length == 2) {
                Integer moticonID = Integer.valueOf(split[0]);
                Integer moticonTimes = Integer.valueOf(split[1]);
                for (Moticon moticon : moticonList) {
                    if (moticon.getId().equals(moticonID)) {
                        moticon.setTimes(moticonTimes);
                    }
                }
            }
        }
    }

    private static void checkFavorites(List<Moticon> moticonList) {
        appPreferences = MoticonsApplication.getAppPreferences();
        for (String string : appPreferences.getMoticonFavorites()) {
            String[] split = string.split("##");
            if (split.length == 2) {
                Integer moticonID = Integer.valueOf(split[0]);
                Boolean moticonFavorite = Boolean.getBoolean(split[1]);
                for (Moticon moticon : moticonList) {
                    if (moticon.getId().equals(moticonID)) {
                        moticon.setFavorite(moticonFavorite);
                    }
                }
            }
        }
    }

    private static void checkUnlocked(List<Moticon> moticonList) {
        appPreferences = MoticonsApplication.getAppPreferences();
        for (String string : appPreferences.getMoticonUnlocked()) {
            Integer moticonID = Integer.valueOf(string);
            for (Moticon moticon : moticonList) {
                if (moticon.getId().equals(moticonID)) {
                    moticon.setUnlocked(true);
                }
            }
        }
    }

    public static Integer increaseMoticonTimes(Moticon moticon) {
        Boolean found = false;
        appPreferences = MoticonsApplication.getAppPreferences();
        Set<String> tempList = appPreferences.getMoticonTimes();
        for (String string : appPreferences.getMoticonTimes()) {
            String[] split = string.split("##");
            if (split.length == 2) {
                Integer moticonID = Integer.valueOf(split[0]);
                if (moticon.getId().equals(moticonID)) {
                    tempList.remove(string);
                    moticon.setTimes(moticon.getTimes() + 1);
                    tempList.add(moticon.toTimes());
                    appPreferences.setMoticonTimes(tempList);
                    found = true;
                }
            }
        }
        if (!found) {
            moticon.setTimes(moticon.getTimes()+1);
            tempList.add(moticon.toTimes());
            appPreferences.setMoticonTimes(tempList);
        }

        return moticon.getTimes();
    }

    public static void addMoticonUnlocked(Moticon moticon) {
        appPreferences = MoticonsApplication.getAppPreferences();
        moticon.setUnlocked(true);
        Set<String> tempList = appPreferences.getMoticonUnlocked();
        tempList.add(moticon.getId().toString());
        appPreferences.setMoticonUnlocked(tempList);
    }

    public static Boolean canBuyMoticon(Moticon moticon) {
        appPreferences = MoticonsApplication.getAppPreferences();
        if (moticon.getMoticoins() <= appPreferences.getMoticoins()) {
            return true;
        } else {
            return false;
        }
    }

    public static void buyMoticon(Context context, Moticon moticon) {
        appPreferences = MoticonsApplication.getAppPreferences();
        addMoticonUnlocked(moticon);
        appPreferences.setMoticoins(appPreferences.getMoticoins() - moticon.getMoticoins());
        MainActivity.updateMoticoins(context);
    }

    public static Integer retrieveMoticoins(List<Moticon> moticonList) {
        Double moticoins = 0.;
        for (Moticon moticon : moticonList) {
            if (!moticon.getUnlocked()) {
                moticoins += moticon.getMoticoins();
            }
        }
        moticoins = moticoins * 0.85;
        return moticoins.intValue();
    }

}
