package kz.orion.sk_orion_springboot.model.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeSubscription;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> search(Visitor vs, TypeSubscription value) {

        List<Subscription> subscriptionList2 = new ArrayList<>();
        for (Subscription sb : subscriptionRepository.findByVisitor(vs)) {
            if (sb.getTypeSubscription() == value) {
                subscriptionList2.add(sb);
            } else {
                continue;
            }
        }
        return subscriptionList2;
    }

    public List<Subscription> returnAllSubscriptionForVisitor(Visitor vs){
        List<Subscription> subscriptionList = subscriptionRepository.findByVisitor(vs);
        return subscriptionList;
    }

    public void save(Subscription subscription) {

        if (null == subscription.getTypeSubscription()) {
            throw new PosException("Пожалуйста, выберите вид абонемента!");
        }else {
            if (subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_POOL ||
                subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_GYM)
            {
                subscription.setNumberOfVisits(12);
            } else if (subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_POOL ||
                    subscription.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_GYM) {
                subscription.setNumberOfVisits(1);
            }
        }

        if (subscription.getDateOfPurchase() == null) {
            throw new PosException("Пожалуйста, установите дату покупки!");
        }
        ;

        if (subscription.getDateOfValidity() == null) {
            throw new PosException("Пожалуйста, установите срок действия!");
        }
        ;

        if (subscription.getPrice() == 0) {
            throw new PosException("Пожалуйста, выберите цену!");
        }

        if (subscription.getGroup() != null) {
            if (subscription.getGroup().getSubscriptionList().size() >= 20) {
                throw new PosException("Превышен лимит количество людей в группе!");
            }
        }


        subscriptionRepository.save(subscription);

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(subscription.getId().toString(), BarcodeFormat.QR_CODE,
                    500, 500);
            MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(subscription.getQrCode()));
        } catch (WriterException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void saveAfterCheck(Subscription subscription){
        subscriptionRepository.save(subscription);
    }

    public Subscription findById(int id){
        Subscription subscription = subscriptionRepository.findById(id);
        return subscription;
    }

}
