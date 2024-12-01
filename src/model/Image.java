package model;

import java.io.Serializable;

public class Image implements Serializable {
    private byte[] data;

    public Image(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
