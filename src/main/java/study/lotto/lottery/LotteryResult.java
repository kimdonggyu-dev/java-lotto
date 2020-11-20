package study.lotto.lottery;

import study.lotto.core.LottoRank;
import study.lotto.core.WinningLotto;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LotteryResult {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    private final Map<LottoRank, Long> countByLottoRank;
    private final double totalReturnRatio;

    public LotteryResult(List<WinningLotto> winningLottos, double totalReturnRatio) {
        this.countByLottoRank = countByLottoRank(winningLottos);
        this.totalReturnRatio = totalReturnRatio;
    }

    private Map<LottoRank, Long> countByLottoRank(List<WinningLotto> winningLottos) {
        return winningLottos.stream()
                .collect(Collectors.groupingBy(WinningLotto::getLottoRank, Collectors.counting()));
    }

    public String getTotalReturnRatio() {
        return DECIMAL_FORMAT.format(totalReturnRatio);
    }

    public Long getNumberOfLottoRank(LottoRank lottoRank) {
        Long numberOfLottoRank = countByLottoRank.get(lottoRank);
        return Objects.nonNull(numberOfLottoRank) ? numberOfLottoRank : 0L;
    }

}
