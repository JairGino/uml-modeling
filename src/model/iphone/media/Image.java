package model.iphone.media;

public class Image {
    private String ASCIIArt;

    public Image(String ASCIIArt) {
        this.ASCIIArt = ASCIIArt;
    }

    public String getASCIIArt() {
        return ASCIIArt;
    }

    public void displayImage() {
        System.out.println("Image: " +
                "ASCIIArt=\n" + ASCIIArt + "\n");
    }
}
