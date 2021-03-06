package ru.foxit.grayfox;

import java.awt.*;

// Создаем класс шарик и наследуем спрайты
public class Ball extends Sprite {

    // нам нужно сделать так, чтобы шарики летали в разные стороны, точнее мы должны задать им координаты
    // мы будем задавать им направление по направления икс и направление игрику
    // Это будет значение смешение по координате х, то есть это будет вектор
    // Если по хорошему, то это надо создавать класс вектор, который будет как то там задавать направленния
    // Мы предлагаем под сократить это дело, мы это сделаем маленькими переменными, это будет значение смещение
    // по координате X, будет значение смещение по координате Y, с течением времени.
    // Это значит что мы умножаем время на скорость и получаем расстояние (получим новые координаты)
    // относительно времени, потому что у нас есть какая то скорость.
    // vx и vy это будет скорость (вектор плюс скорость).
    // и скорость у нас будет случайная Math.random, за секунду мы будем по иксу мы будем смешаться на 150 пикселей + до
    // 349 пикселей в секунду.
    private float vx = 150 + (float)(Math.random() * 200f);
    private float vy = 150 + (float)(Math.random() * 200f);

    // Мы создаем приватный финальный колор и делаем вообще случайный цвет шарика.
    // то есть вообще 3 случайных числа до 255 не включая "255"
    private final Color color = new Color(
            (int) (Math.random() * 255f),
            (int) (Math.random() * 255f),
            (int) (Math.random() * 255f));


    // Конструктор шарика
    public Ball() {
        // от 20 до 69 пикселей у нас будут размеры шарика
        halfHeight = 20 + (float)(Math.random() * 50f);
        // и он будет кругленький
        halfWidth = halfHeight;
    }
    ////////////////////////
    Ball(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }


    ////////////////////////

    // Мы переопределяем ничего не деланье - рендрер
    @Override

    // Рендер будет рисовать
    public void render(GameCanvas gameCanvas, Graphics g) {
        // Установка колора (сет колор)
        g.setColor(color);
        // Только рендер нашего шарика знает, что наш шарик круглый и что он заполнен.
        // И что он с координат левый верхний угол и размером ширина высота.
        // Только этот метод вкурсе, как рисовать шарики.
        // И этот метод находиться внутри шарика.
        g.fillOval((int)getLeft(), (int)getTop(),
                (int)getWidth(), (int)getHeight());
    }

    // и переопределяем апдейт, чтобы тут задать свое поведение, ну и в рендер собственно.
    @Override
    public void update(GameCanvas gameCanvas, float deltaTime) {
        // Коодината по X будет новая, расстояние равно скорость умноженная на время.
        x += vx * deltaTime;
        // Коодината по Y будет новая, расстояние равно скорость умноженная на время.
        y += vy * deltaTime;
        // Условие, если getLeft (геттер влево) меньше чем gameCanvas(класс канвы).getLeft
        // Если наша граница шарика (левая) вышла за границу нашей игровой конвы
        if (getLeft() < gameCanvas.getLeft()) {
            // то нам нужно установить левую границу нашего шарика на левую границу нашей конвы
            setLeft(gameCanvas.getLeft());
            // и сменить направление по X
            vx = -vx;
        }
        // Правая граница проверка
        if(getRight() > gameCanvas.getRight()){
            setRight(gameCanvas.getRight());
            vx = -vx;
        }
        // верхняя граница проверка
        if(getTop() < gameCanvas.getTop()){
            setTop(gameCanvas.getTop());
            vy = -vy;
        }
        // нижняя граница проверка
        if(getBottom() > gameCanvas.getBottom()){
            setBottom(gameCanvas.getBottom());
            vy = -vy;
        }
    }
}