package model.iphone.phone;

import model.iphone.media.Music;

public interface Phone {
    public void changeRingtone(Music music);
    public void makeCall(String phoneNumber);
    public void makeCall(Contact contact);
    public void endCurrentCall();
    public void acceptIncomingCall(Call call);
    public void rejectIncomingCall(Call call);
}
