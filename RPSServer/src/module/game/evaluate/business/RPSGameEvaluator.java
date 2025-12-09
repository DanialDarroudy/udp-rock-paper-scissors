package module.game.evaluate.business;

import module.game.common.model.PlayerMove;
import module.game.common.model.ResultMove;
import module.game.evaluate.abstraction.IGameEvaluator;

public class RPSGameEvaluator implements IGameEvaluator {
    @Override
    public ResultMove evaluate(PlayerMove firstPlayerMove, PlayerMove secondPlayerMove) {
        if (firstPlayerMove.getCode() == secondPlayerMove.getCode()){
            return ResultMove.DRAW;
        }
        return ((firstPlayerMove.getCode() + 1) % 3 == secondPlayerMove.getCode())
                ? ResultMove.Second_Player_WIN
                : ResultMove.First_Player_WIN;
    }
}
