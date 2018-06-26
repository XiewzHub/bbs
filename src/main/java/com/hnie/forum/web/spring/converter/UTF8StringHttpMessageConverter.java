package com.hnie.forum.web.spring.converter;

/**
 * Created by xiewz on 2018/6/26.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

public class UTF8StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private final Charset defaultCharset;
    private final List<Charset> availableCharsets;
    private boolean writeAcceptCharset;

    public UTF8StringHttpMessageConverter() {
        this(DEFAULT_CHARSET);
    }

    public UTF8StringHttpMessageConverter(Charset defaultCharset) {
        super(new MediaType[]{new MediaType("text", "plain", defaultCharset), MediaType.ALL});
        this.writeAcceptCharset = true;
        this.defaultCharset = defaultCharset;
        this.availableCharsets = new ArrayList(Charset.availableCharsets().values());
    }

    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }

    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    protected String readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException {
        Charset charset = this.getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));
    }

    protected Long getContentLength(String s, MediaType contentType) {
        Charset charset = this.getContentTypeCharset(contentType);

        try {
            return (long)s.getBytes(charset.name()).length;
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException(var5);
        }
    }

    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        if (this.writeAcceptCharset) {
            outputMessage.getHeaders().setAcceptCharset(this.getAcceptedCharsets());
        }

        Charset charset = this.getContentTypeCharset(outputMessage.getHeaders().getContentType());
        FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(), charset));
    }

    protected List<Charset> getAcceptedCharsets() {
        return this.availableCharsets;
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        return contentType != null && contentType.getCharSet() != null ? contentType.getCharSet() : this.defaultCharset;
    }
}
