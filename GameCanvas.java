// Пакет
package ru.foxit.grayfox;

// Импорты
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

// Создан класс GameCanvas наследует JPanel (Доску/панель для рисование)
public class GameCanvas extends JPanel {
    // Берем классовую константу чтобы сохранить
    private final MainWindow mainWindow;
    private long lastFrameTime;

    // и в конструкторе надо их принять

    GameCanvas(MainWindow mainWindow) {
        //(1*) в куче все сохраняется и ничего не трогается
        //this - обращение к классу mainWindow
        this.mainWindow = mainWindow;
        lastFrameTime = System.nanoTime();
    }

    // При помощи метода paintComponent вот эти панельки умеют рисовать. Когда панелька рисует,
    // она вызывает этот метод и мы хотим переопределить это поведение, добавить свое поведение (полиморфизм)
    // Оно как то умеет рисоваться, но нам нужно добавить сюда свои обобенности.
    @Override
    // Этот метот будет вызываться и когда то работать, в процессе работы...
    // Переменные ниже будет попадать в стек и по выходу из стека, эти все переменные
    // будут выкидываться. В стек все попадает и выкидывается (1*)
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        // Берем текущее время в нано секундах, создаем время и все.
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        // Нам нужна переменная lastFrameTime, из текущее время вычесть то время.
        lastFrameTime = currentTime;
        // mainWindow (класс), onDrawFrame (метод) передаем this, передаем объект графики, передаем дельта времени.
        //this - мы вызываем из этого объекта, из этой конвы, по этому мы передаем этот объект this, из которого
        // мы вызываем onDrawFrame
        //Background background = new Background();
        //background.setColor( this, g);
        mainWindow.onDrawFrame(this, g, deltaTime);
        // трай кетч делаем (пусть пока это будет магия).
        try {
            // 1 секунду поделить на 60, примерно 17 секунд - чтобы мы получили 60 кадров в секунду.
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Мы делаем бессконечный цикл в отрисовке канвы
        // Мы вызываем метод repaint в paintComponent, который ставит paintComponent в очередь окна.
        // То есть мы постоянно будем перерисовывать бессконечно это окно
        repaint();
    }

    // Напишем 4 метода (Да, геттеры это методы), меня int чуть не запутал, чуть не написал что это переменные...
    // Это левая граница 0 по x, правая граница 0 по y, правая граница это ширина -1, нижняя граница это высота -1

    // Это для того чтобы шарики отскакивали (но это не точно).
    int getLeft() { return 0; }
    int getRight() { return getWidth() - 1; }
    int getTop() { return 0; }
    int getBottom() { return getHeight() - 1; }


}