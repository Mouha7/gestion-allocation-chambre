package ucad.glrs.view;

import java.util.List;
import java.util.Scanner;

import ucad.glrs.core.View;

public abstract class Implementation<T> implements View<T> {
    protected Scanner scanner = new Scanner(System.in);

    @Override
    public void affiche(List<T> datas) {
        for (T data : datas) {
            System.out.println(data);
        }
    }
}
