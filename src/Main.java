import model.iphone.Iphone;
import model.iphone.media.Image;
import model.iphone.media.Music;
import model.iphone.phone.Call;
import model.iphone.phone.Contact;

public class Main {
    public static void main(String[] args) {
        Iphone myIphone = new Iphone();

        String coverFlowMusic =
                "      :::        ::::::::       ::::::::::       ::::::::::: \n" +
                "     :+:       :+:    :+:      :+:                  :+:      \n" +
                "    +:+       +:+    +:+      +:+                  +:+       \n" +
                "   +#+       +#+    +:+      :#::+::#             +#+        \n" +
                "  +#+       +#+    +#+      +#+                  +#+         \n" +
                " #+#       #+#    #+#      #+#                  #+#          \n" +
                "########## ########       ###              ###########       ";
        myIphone.addMusic(new Music("lofi hip hop", new Image(coverFlowMusic)));

        Contact friendContact = new Contact("John Doe", "(11) 91111-1111");
        myIphone.addContact(friendContact);
        Call unkownCall = new Call("(11) 92222-2222");

//     ----------------------    CENÁRIO DE UTILIZAÇÃO    ----------------------
        myIphone.findMusic("lofi hip hop").getCoverFlow().displayImage();

        myIphone.playMusic(myIphone.findMusic("lofi hip hop"));

        myIphone.makeCall(friendContact);

        myIphone.acceptIncomingCall(unkownCall);

        myIphone.mergeCalls();

        myIphone.splitCall(myIphone.getCurrentCall());

        myIphone.endCurrentCall();

        myIphone.endCurrentCall();

    }
}
