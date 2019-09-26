



@Api(tags = "微信授权登录")
@RestController
@RequestMapping("/wx/oauth")
public class WxOauthController {

    private final String clientId = "123456";

    private final String clientSecret = "123456";

    private final String redirectUri = "http://localhost:60837/api/wx/oauth/callback";

    private final String state = "wx";

    @ApiOperation("微信授权")
    @GetMapping("/render")
    public void renderAuth (HttpServletResponse response)throws IOException {
        AuthWeChatRequest authWeChatRequest = getAuthWeChatRequest();
        response.sendRedirect(authWeChatRequest.authorize(state));
    }

    @GetMapping("/callback")
    public AuthResponse login(AuthCallback callback){
        AuthWeChatRequest authWeChatRequest = getAuthWeChatRequest();
        return authWeChatRequest.login(callback);
    }

    private AuthWeChatRequest getAuthWeChatRequest(){

        return new AuthWeChatRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }

}
