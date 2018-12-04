package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

public class khiem extends Enemy {
    public khiem(int x, int y, Board board) {
        super(x, y, board, Sprite.khiem_dead, Game.getBomberSpeed(), 100);

        _sprite = Sprite.khiem_left1;

        _ai = new AIMedium(_board.getBomber(), this);
        _direction = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                if(_moving)
                    _sprite = Sprite.movingSprite(Sprite.khiem_right1, Sprite.khiem_right2, Sprite.khiem_right3, _animate, 60);
                else
                    _sprite = _sprite.khiem_right1;
                break;
            case 2:
            case 3:
                if(_moving)
                    _sprite = Sprite.movingSprite(Sprite.khiem_left1, Sprite.khiem_left2, Sprite.khiem_left3, _animate, 60);
                else
                    _sprite = Sprite.khiem_left1;
                break;
        }
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý va chạm với Flame
        // TODO: xử lý va chạm với Bomber
        if(e instanceof Flame) {
            kill();
            _board.addCharacter( new Balloon((int)this.getX(), (int)this.getY(), _board));
            _board.addCharacter( new Balloon((int)this.getX(), (int)this.getY(), _board));

            return false;
        }

        if(e instanceof Bomber) {
            ((Bomber) e).kill();
            return false;
        }

        return true;
    }
}
