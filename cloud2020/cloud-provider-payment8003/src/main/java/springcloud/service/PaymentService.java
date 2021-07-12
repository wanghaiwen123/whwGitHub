package springcloud.service;

import com.whwxuexi.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther zzyy
 * @create 2020-01-27 21:11
 */
public interface PaymentService
{
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    List<Payment> getPaymentByList();
}
