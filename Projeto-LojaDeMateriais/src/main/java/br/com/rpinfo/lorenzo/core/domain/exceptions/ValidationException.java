package br.com.rpinfo.lorenzo.core.domain.exceptions;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class ValidationException extends Exception{
    private Set<String> messages;

    public ValidationException(String message) {
        super(message);
        this.setMessage(message);
    }

    private void setMessage(String message){
        this.messages = new LinkedHashSet<>();
        this.messages.add(message);
    }

    @Generated
    public Set<String> getMessages() { return this.messages; }
}

