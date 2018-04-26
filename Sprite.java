package ru.foxit.grayfox;

import java.awt.*;

// Что должен знать объект в нашей программе, в нашем движке, что должен знать нарисованных объект о себе?
// Создаем публичный класс спрайт
public class Sprite implements GameObject {
    float x;    // x of the center of sprite
    float y;    // y center
    protected float vY;
    protected float vX;
    // Мы ограничим все спрайты квадратами, чтобы туда вписать свои формулы.
    // Нам удобно хранить координаты, когда мы знаем что это центр нашего прямоугольника.
    // Половина ширины
    float halfHeight;
    // половина высоты
    float halfWidth;

    // нам нужны сложные методы, которые будут возвращать левую, правую, верхнюю, нижние границы спрайта
    // и устанавливать  левую, правую, верхнюю, нижние границы спрайта
    // а так же тут нужны нам геттеры, которые вернут ширину и высоту нашего спрайта целиком.
    // 2 умноженная на полувысоту и 2 умноженная на полуширину.
    float getLeft(){ return x - halfWidth; }
    void setLeft(float left){ x = left + halfWidth; }
    float getRight(){ return x + halfWidth; }
    void setRight(float right){ x = right - halfWidth;}
    float getTop(){ return y - halfHeight; }
    void setTop(float top){ y = top + halfHeight; }
    float getBottom(){ return y + halfHeight; }
    void setBottom(float bottom){ y = bottom - halfHeight; }
    void revertVx() { this.vX = -this.vX; }
    void revertVy() { this.vY = -this.vY; }

    float getWidth(){ return 2f * halfWidth; }
    float getHeight(){ return 2f * halfHeight; }

    // Что умеет спрайт, это рендериться (рисовать себя)
    @Override
    public void render(GameCanvas gameCanvas, Graphics g) {/*do nothing*/}
    // и обновляться (передвигаться - как то менять свое состояние)
    @Override
    public void update(GameCanvas gameCanvas, float deltaTime) {}

}