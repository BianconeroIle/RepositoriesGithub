package com.example.ilijaangeleski.repositoriesgithub;

import com.example.ilijaangeleski.repositoriesgithub.api.NetworkApi;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepositories;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;

public class NetworkApiTest {

    @Test
    public void testApiResponse() {
        NetworkApi mockedApiInterface = Mockito.mock(NetworkApi.class);
        final Call mockedCall = Mockito.mock(Call.class);

        Mockito.when(mockedApiInterface.fetchRepositories("showbox")).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<List<GitRepositories>> callback = invocation.getArgument(0);
                callback.onResponse(mockedCall, Response.success((List<GitRepositories>)
                        new ArrayList<GitRepositories>()));

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
    }
}