import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationStatsTest {

    @Test(expected = IllegalArgumentException.class)
    public void with_negative_n_should_not_create_percolation_stat() {
        new PercolationStats(-23, 42);
    }

    @Test(expected = IllegalArgumentException.class)
    public void with_0_trial_should_not_create_percolation_stat() {
        new PercolationStats(23, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void with_negative_n_and_0_trial_should_not_create_percolation_stat() {
        new PercolationStats(-42, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void with_minus_1_trial_should_not_create_percolation_stat() {
        new PercolationStats(42, -1);
    }
}