package business.abstracts;

import entities.Brand;
import entities.CellPhone;
import entities.NoteBook;

public interface StoreService {
    void addBrand();
    void listBrand();
    void addCategory();
    void listProducts();
    void addNoteBook();
    void addCellPhone();
    void listProductWihId(int id);
    void listProductWihBrand(int brandId);
    void listProductByCategory(int categoryId);
    void addNewNoteBook(NoteBook noteBook);
    void addNewCellPhone(CellPhone cellPhone);
    void deleteNoteBook(int id);
    void deleteCellPhone(int id);
    void listNoteBook();
    void listCellPhone();
    void listBrandById();
    Brand getBrandById(int brandId);

}
