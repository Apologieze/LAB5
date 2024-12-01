package model;

public class Image {
    private byte[] data;

    public Image(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
