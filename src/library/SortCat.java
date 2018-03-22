package library;

import java.util.ArrayList;

import model.bean.Category;

public class SortCat {
	public static ArrayList<Category> sort(ArrayList<Category> listCat){
		for (int id_Parent = 0; id_Parent < listCat.size(); id_Parent++) {
			if(listCat.get(id_Parent).getIdParent() > 0){
				for (int idCat = 0; idCat < listCat.size() - 1; idCat++) {
					if(listCat.get(id_Parent).getIdParent() == listCat.get(idCat).getIdCat()){
						listCat.add(idCat + 1, listCat.get(id_Parent));
						listCat.remove(id_Parent + 1);
						break;
					}
				}
			}
		}
		return listCat;
	}
	
}
