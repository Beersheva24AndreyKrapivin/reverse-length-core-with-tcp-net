package telran.appl.net;

import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class ReverseLengthProtocol implements Protocol{

    @Override
    public Response getResponse(Request request) {
        Response response = null;
        String type = request.requestType();
        if (type.equals("reverse")) {
            String reverseString = new StringBuilder(request.requestData()).reverse().toString();
            response = new Response(ResponseCode.OK, "Reverse string for " + request.requestData() + " is: " + reverseString);
        } else if (type.equals("length")) {
            response = new Response(ResponseCode.OK, "Lenght string " + request.requestData() + " = " + request.requestData().length());
        } else {
            response = new Response(ResponseCode.WRONG_TYPE, request.requestData());
        }
        return response;
    }

}
