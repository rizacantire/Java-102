package business.abstracts;

import entities.CellPhone;
import entities.NoteBook;

public interface StoreService {
    void addBrand();
    void listBrand();
    void addCategory();

    void addNoteBook();
    void addCellPhone();
    void addNewNoteBook();
    void addNewCellPhone();
    void listNoteBook();
    void listCellPhone();

}
