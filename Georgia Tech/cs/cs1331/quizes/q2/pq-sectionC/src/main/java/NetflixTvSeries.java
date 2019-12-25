public class NetflixTvSeries extends TvSeries implements Bingeable {

    public NetflixTvSeries(String title, int episodeLength, int numberEpisodes)
    {
        super(title, episodeLength, numberEpisodes);
    }

    public String binge() {
        int total = super.getNumberOfEpisodes() * super.getEpisodeLength();
        return "You binge watch " + super.getTitle() + ". " + "It takes "
                + total + " minutes to binge.";
    }
}
