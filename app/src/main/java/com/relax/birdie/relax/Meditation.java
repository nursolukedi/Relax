package com.relax.birdie.relax;

public class Meditation {
    public static class Meditate
    {
        private String meditationName;
        private int pictureId;

        public Meditate( String meditationName, int pictureId)
        {
            this.meditationName = meditationName;
            this.pictureId = pictureId;
        }

        public int getPicture() {
            return pictureId;
        }

        public String getMeditationName()
        {
            return meditationName;
        }

        public String toString(){
            return meditationName; }


    }

    public static Meditation.Meditate[] meditations= {
            new Meditate("Breathing", R.drawable.yoga),
            new Meditate("Go outside",R.drawable.meditation)};

}
