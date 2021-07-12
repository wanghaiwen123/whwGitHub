package springcloud.service.impl;

import com.whwxuexi.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import springcloud.dao.PaymentDao;
import springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther zzyy
 * @create 2020-01-27 21:12
 */
@Service
public class PaymentServiceImpl implements PaymentService
{
    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment)
    {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id)
    {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public List<Payment> getPaymentByList() {
        return paymentDao.getPaymentByList();
    }
}
