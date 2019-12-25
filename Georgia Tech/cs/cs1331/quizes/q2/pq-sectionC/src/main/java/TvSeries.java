public abstract class TvSeries {
    private String title;
    private int episodeLength;
    private int numberEpisodes;

    public TvSeries(String title, int episodeLength, int numberEpisodes) {
        this.title = title;
        this.episodeLength = episodeLength;
        this.numberEpisodes = numberEpisodes;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeLength() {
        return episodeLength;
    }

    public int getNumberOfEpisodes() {
        return numberEpisodes;
    }

    @Override
    public String toString() {
        return title + " has " + numberEpisodes + " where each episode is "
                    + episodeLength + " minutes long.";
    }
}
