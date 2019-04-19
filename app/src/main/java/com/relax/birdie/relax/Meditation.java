package com.relax.birdie.relax;

public class Meditation {

    //meditation object.

    public static class Meditate
    {
        private String meditationName;
        private int pictureId;
        private String meditationMoodType;
        private String meditationLevel;
        private String meditationLink;

        public Meditate( String meditationName, int pictureId , String meditationType, String meditationLevel )
        {
            this.meditationName = meditationName;
            this.pictureId = pictureId;
            this.meditationMoodType = meditationType;
            this.meditationLevel = meditationLevel;

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

        public String getMeditationMoodType() {
            return meditationMoodType;
        }

        public String getMeditationLevel() {
            return meditationLevel;
        }

        public String getMeditationLink()
        {
            return meditationLink;
        }
        public void setMeditationLink(String newLink)
        {
            meditationLink = newLink;
        }
    }

    public static Meditation.Meditate[] meditations= {
            new Meditate("Breathing", R.drawable.yoga, "happy", "low" ),
            new Meditate("Call a friend", R.drawable.yoga, "happy", "low" ),
            new Meditate("Mindfullness exercise", R.drawable.yoga, "happy", "low" ),
            new Meditate("Talk to your friend", R.drawable.yoga, "calm", "low"),
            new Meditate("Set timer for your work", R.drawable.yoga, "calm", "low"),
            new Meditate("Focus on your work", R.drawable.yoga, "calm", "low"),
            new Meditate("Mindfullness exercise ", R.drawable.yoga,"sad" , "normal"),
            new Meditate("Go to cinema yourself ", R.drawable.yoga,"sad" , "normal"),
            new Meditate("Call a friend about it ", R.drawable.yoga,"sad" , "normal"),
            new Meditate("Try kickboxing" , R.drawable.meditation, "stressed", "high"),
            new Meditate("Go outside",R.drawable.meditation , "stressed", "high"),
            new Meditate("Spare time for gym", R.drawable.meditation, "stressed", "high")};


}
