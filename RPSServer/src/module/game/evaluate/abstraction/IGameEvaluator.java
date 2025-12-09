package module.game.evaluate.abstraction;

import module.game.common.model.PlayerMove;
import module.game.common.model.ResultMove;

public interface IGameEvaluator {
    ResultMove evaluate(PlayerMove firstPlayerMove, PlayerMove secondPlayerMove);
}
