package entities;

import java.util.Collections;

public class NoteBook extends Product implements Comparable<NoteBook> {
    private int noteBookId;
    private int memorySize;
    private double storageSize;
    private double screenSize;

    public NoteBook(String productName, int categoryId, double unitPrice, double discount, int unitsInStock, Brand brandName, int noteBookId, int memorySize, double storageSize, double screenSize) {
        super(productName, categoryId, unitPrice, discount, unitsInStock, brandName);
        this.noteBookId = noteBookId;
        this.memorySize = memorySize;
        this.storageSize = storageSize;
        this.screenSize = screenSize;
    }

    public int getNoteBookId() {
        return noteBookId;
    }

    public void setNoteBookId(int noteBookId) {
        this.noteBookId = noteBookId;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public double getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(double storageSize) {
        this.storageSize = storageSize;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }


    @Override
    public int compareTo(NoteBook o) {
        if(this.getNoteBookId()<o.getNoteBookId()){
            return -1;
        }
        else {
            return 1;
        }
    }
}
