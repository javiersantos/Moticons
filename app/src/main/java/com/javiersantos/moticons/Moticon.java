package com.javiersantos.moticons;

import java.util.List;

public class Moticon {
    private String moticon;
    private MoticonCategory category;
    private List<MoticonKeywords> keywords;
    private Integer times;
    private Boolean favorite;
    private Integer moticoins;
    private Boolean unlocked;

    public Moticon(String moticon, MoticonCategory category, List<MoticonKeywords> keywords, Integer moticoins) {
        this.moticon = moticon;
        this.category = category;
        this.keywords = keywords;
        this.times = 0;
        this.favorite = false;
        this.moticoins = moticoins;
        this.unlocked = false;
    }

    public Moticon(String moticon, MoticonCategory category, List<MoticonKeywords> keywords) {
        this.moticon = moticon;
        this.category = category;
        this.keywords = keywords;
        this.times = 0;
        this.favorite = false;
        this.moticoins = 0;
        this.unlocked = true;
    }

    public String getMoticon() {
        return moticon;
    }

    public void setMoticon(String moticon) {
        this.moticon = moticon;
    }

    public MoticonCategory getCategory() {
        return category;
    }

    public void setCategory(MoticonCategory category) {
        this.category = category;
    }

    public Integer getId() {
        return moticon.hashCode();
    }

    public List<MoticonKeywords> getKeywords() {
        return keywords;
    }

    public void addKeyword(MoticonKeywords keyword) {
        this.keywords.add(keyword);
    }

    public void removeKeyword(MoticonKeywords keywords) {
        this.keywords.remove(keywords);
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String toTimes() {
        return getId() + "##" + getTimes();
    }

    public String toFavorite() {
        return getId() + "##" + getFavorite();
    }

    public Integer getMoticoins() {
        return moticoins;
    }

    public void setMoticoins(Integer moticoins) {
        this.moticoins = moticoins;
    }

    public Boolean getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }
}

