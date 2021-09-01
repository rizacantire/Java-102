package business.abstracts;

import entities.CellPhone;
import entities.NoteBook;

public interface ProductService {

    void listProduct();
    void deleleteNotebookById(int id);
    void deleleteCellPhoneById(int id);
    void listProductByCategory(int categoryId);
    void listProductWihId(int id);
    void listProductWihBrand(int brandId);

    void addNoteBook(NoteBook book);
    void addCellPhone(CellPhone cellPhone);
    void listNoteBook();
    void listCellPhone();
}
