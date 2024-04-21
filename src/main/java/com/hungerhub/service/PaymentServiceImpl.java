package com.hungerhub.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hungerhub.model.Order;
import com.hungerhub.response.PaymentResponse;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse createPaymentLink(Order order) {
        Stripe.apiKey = stripeSecretKey;

        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3000/payment/success/" + order.getId())
                    .setCancelUrl("http://localhost:3000/payment/fail")
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("inr")
                                            .setUnitAmount((long) (order.getTotalPrice() * 100))
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName("Hunger Hub")
                                                            .build()
                                            )
                                            .build()
                            )
                            .build()
                    )
                    .build();

            Session session = Session.create(params);

            PaymentResponse res = new PaymentResponse();
            res.setPaymentUrl(session.getUrl());
            return res;
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}



// package com.hungerhub.service;

// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.exception.InvalidRequestException;
// import com.stripe.model.Customer;
// import com.stripe.model.PaymentIntent;
// import com.stripe.model.checkout.Session;
// import com.stripe.param.CustomerCreateParams;
// import com.stripe.param.PaymentIntentCreateParams;
// import com.stripe.param.checkout.SessionCreateParams;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.hungerhub.model.Order;
// import com.hungerhub.response.PaymentResponse;

// @Service
// public class PaymentServiceImpl implements PaymentService {

//     @Value("${stripe.api.key}")
//     private String stripeSecretKey;

//     @Override
//     public PaymentResponse createPaymentLink(Order order) {
//         Stripe.apiKey = stripeSecretKey;

//         try {
//             CustomerCreateParams params2 =
//             CustomerCreateParams.builder()
//               .setName("Jenny Rosen")
//               .setAddress(
//                 CustomerCreateParams.Address.builder()
//                   .setLine1("510 Townsend St")
//                   .setPostalCode("98140")
//                   .setCity("San Francisco")
//                   .setState("CA")
//                   .setCountry("US")
//                   .build()
//               )
//               .build();
          
//           Customer customer = Customer.create(params2);
//             // Create a customer
//             SessionCreateParams params = SessionCreateParams.builder()
//             .setCustomer(customer.getId()) // Use the ID of the created customer
//             .setSuccessUrl("https://your-website.com/success") // Redirect URL after successful payment
//             .setCancelUrl("https://your-website.com/cancel") // Redirect URL on cancellation
//             .setMode(SessionCreateParams.Mode.PAYMENT) // Set mode to payment
//             .addLineItem(
//                 SessionCreateParams.LineItem.builder()
//                     .setPriceData(
//                         SessionCreateParams.LineItem.PriceData.builder()
//                             .setProductData(
//                                 SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                                     .setName("Your product name")
//                                     .build())
//                             .setCurrency("usd") // Set currency
//                             .setUnitAmount((long) 1000) // Set price in cents (example: $10.00)
//                             .build())
//                     .setQuantity((long) 1) // Set quantity (optional)
//                     .build())
//             .build();
          
//           // Create the checkout session
//           Session session = Session.create(params);
          
//           // Get the payment URL from the session object
//           String paymentUrl = session.getUrl();
//             PaymentResponse res = new PaymentResponse();
//             res.setPaymentUrl(paymentUrl);
//             return res;
//         } catch (InvalidRequestException e) {
//             // Handle specific exception for invalid request (e.g., due to regulatory restrictions)
//             return handleInvalidRequestException(e);
//         } catch (StripeException e) {
//             // Handle other Stripe exceptions
//             e.printStackTrace();
//             return null;
//         }
//     }

//     private PaymentResponse handleInvalidRequestException(InvalidRequestException e) {
//         PaymentResponse res = new PaymentResponse();
//         // Customize error message based on the specific exception
//         //res.setError("Failed to create payment link: " + e.getMessage());
//         return res;
//     }
// }


