// Пакет
package ru.foxit.grayfox;

// Писать будет на свинге

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// todo | 1 Полностью разобраться с кодом | Готово.
// todo | 2 Прочитать прикреплённую методичку к следующему занятию | Прочитал на планшете.

// Создаем класс с именем MainWindow наследуем JFrame (окно).
public class MainWindow extends JFrame {

    // Создаем приватный (только в этом классе), статический (принадлежит классу)
    // Финальное (не изменяемую переменную) инт с размером окна и координатам,
    // где наше окно будет расположено.
    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    // Создаем класс Main
    public static void main(String[] args) {
        // Используем класс SwingUtilities, статический метод invokeLater
        // и который принимает новый Runnable
        // Оказывается Runnable это интерфейс, у которого есть метод run
        // и мы переопределяем поведение этого метода.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // И вот тут мы создаем свое окно.
                new MainWindow();
            }
        });
    }

    // Создаем приватный конструктор MainWindow, в котором мы опишем весь функционал окна.
    private MainWindow() {
        // Закрытие окна EXIT_ON_CLOSE, можно 3, но это не рекомендуется.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Устанавливаем позицию окна
        setLocation(POS_X, POS_Y);
        // Устанавливаем размер окна
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // Свойства, что нельзя менять размер окна.
        setResizable(false);
        // Название окна
        setTitle("Circles");
        // Создаем GameCanvas (на котором мы будем рисовать)
        GameCanvas gameCanvas = new GameCanvas(this);
        //////new///////
        gameCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Добавления спрайта
                    addSprite(new Ball(e.getX(), e.getY()));
                } else if (e.getButton() == MouseEvent.BUTTON3) { // Удаление спрайта
                    removeSprite();
                }

            }
        });
        //////end_new///////
        // Добавим лист бумаги gameCanvas в наше окошко
        add(gameCanvas);
        // Будьте любезны, заполните массив на 10 спрайтов, создайте на нашей доске 10 объектов.
        initGame();
        // Тогда вообще не отобразиться программа, если не написать setVisible(true);
        addSprite(new Background()); // тут должны добавляться бекграунд.
        addSprite(new Ball());
        setVisible(true);
    }

    // Создадим массив из спрайтов, мы скажем
    // В ней будет 10 спрайтов
    // изменили на 1.
    private Sprite[] sprites = new Sprite[1];
    ///////////////new///////////////
    private int spritesCount;

    private void addSprite(Sprite sprite) {
        //Если наше колличество спрайтов перевалилось за кол/во массива, то
        if (spritesCount == sprites.length) {
            Sprite[] newSprites = new Sprite[sprites.length * 2];
            System.arraycopy(sprites, 0, newSprites, 0, sprites.length);
            // мы перестаем пользоваться новым спрайтом, старый попадает под сборку мусора, новый живет своей жизнью.
            sprites = newSprites;
        }
        sprites[spritesCount] = sprite;
        spritesCount++;
    }

    ///////////////end_new///////////////

    private void removeSprite() {
        //sprites[--spritesCount] = null; // Удалить ссылку на объект, не рекомендуется.
        if (spritesCount > 1)
            spritesCount--;

    }


    // при старте нам нужно заполнить массив, а точнее делаем так, чтобы при старте программы наши 10 шариков
    // появились.
    void initGame() {
        // Если i = 0, i меньше чем sprites.length кол-во спрайтов, i++ (+1)
        //for (int i = 0; i < sprites.length; i++) {
        // И добавляем новый шарик, заполняя массив до конца.
        // Мы не создаем какие то абстрактные спрайты, мы создаем конкретные наши шарики, но кладем их
        // в массив спрайтов, потому что наш шарик является наследником спрайтов.
        //sprites[i] = new Ball();
        addSprite(new Ball());
        //}
    }
    // Создаем метод onDrawFrame будет нам говорить, что же должно происходить, когда отрисовался фрейм.
    // Действия пользователей, логика, отрисовка

    // Нам нужно gameCanvas (Нам нужна gameCanvas таймер), нам нужен объект графики Graphics и дельта тайм
    // флоут
    // Когда отриуется, мы будем знать где рисовать, при помощи чего рисовать и на сколько все менять (время).
    void onDrawFrame(GameCanvas gameCanvas, Graphics g, float deltaTime) {
        // Он будет заниматься обновлением всех компонентов, которые находятся у нас на окошке
        // все объекты будут отрисовываться, двигаться не двигаться, менять цвет и еще что то делать.
        // update нужны аргумент gameCanvas (где рисоваться) - и время в дельта секундах (на сколько все менять).
        update(gameCanvas, deltaTime);
        // Рендер будет рисовать
        // GameCanvas будет таймером, щелкать
        // render нужен таймер gameCanvas (где рисоваться) и графика (как рисоваться)
        render(gameCanvas, g);
    }

    // Заготовка рендер
    private void render(GameCanvas gameCanvas, Graphics g) {
        // Нарисуем заполненный авал от 00 до 100, тут был код, но его удалили.
        // Пришло пора рендериться, заполните массив.
        // int i = 0; i < sprites.length; i++
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].render(gameCanvas, g);
        }
    }

    // заготовка апдейт
    private void update(GameCanvas gameCanvas, float deltaTime) {
        // Давайте ка вы будете апдейтиться, заполните массов.
        // int i = 0; i < sprites.length; i++
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].update(gameCanvas, deltaTime);
        }
    }

}