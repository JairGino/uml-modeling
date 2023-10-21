package model.iphone.media;

public class Music {
    private String name;
    private Image coverFlow;

    public Music(String name, Image coverFlow) {
        this.name = name;
        this.coverFlow = coverFlow;
    }

    public String getName() {
        return name;
    }

    public Image getCoverFlow() {
        return coverFlow;
    }

    public void play() {
        System.out.println("Playing music: " + this);
    }

    public void pause() {
        System.out.println("Pausing music: " + this);
    }

    public void terminate() {
        System.out.println("Terminating music: " + this);
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                '}';
    }
}
