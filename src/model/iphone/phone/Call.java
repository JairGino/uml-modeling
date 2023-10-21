package model.iphone.phone;

import java.util.HashSet;
import java.util.Set;

public class Call {
    protected Set<Contact> callParticipants = new HashSet<Contact>();
    private Boolean mute;
    private Boolean speakerphone;

    public Call(String phoneNumber) {
        this.callParticipants.add(new Contact("desconhecido", phoneNumber));
        this.mute = false;
        this.speakerphone = false;
    }

    public Call(Contact contact) {
        this.callParticipants.add(contact);
        this.mute = false;
        this.speakerphone = false;
    }

    public void toggleMute() {
        mute = !mute;
        System.out.println("Microfone mutado " + ((mute) ? "ativado" : "desativado"));
    }

    public void toggleSpeakerphone() {
        speakerphone = !speakerphone;
        System.out.println("Viva voz " + ((speakerphone) ? "ativado" : "desativado"));
    }

    public void endCall() {
        System.out.println("Chamada finalizada: " + this);
        callParticipants.clear();
    }

    public void hold() {
        System.out.println("Chamada em espera: " + this);
    }

    public Set<Contact> getParticipants() {
        return this.callParticipants;
    }

    public void addParticipant(Contact participant) {
        if (this.callParticipants.contains(participant)) {
            System.out.println("O participante: " + participant + "já está participando da chamada!");
            return;
        }
        System.out.println("    Adicionando " + participant + " a chamada");
        this.callParticipants.add(participant);
    }

    @Override
    public String toString() {
        return "Call{" +
                "callParticipants=" + callParticipants +
                '}';
    }
}
