package uk.org.esig.adobe.widgets;

import io.swagger.client.api.BaseUrisApi;
import io.swagger.client.api.GroupsApi;
import io.swagger.client.api.UsersApi;
import io.swagger.client.api.WidgetsApi;
import io.swagger.client.model.ApiClient;
import io.swagger.client.model.ApiException;
import io.swagger.client.model.baseUris.BaseUriInfo;
import io.swagger.client.model.groups.GroupInfo;
import io.swagger.client.model.groups.GroupsInfo;
import io.swagger.client.model.users.DetailedUserInfo;
import io.swagger.client.model.users.UserInfo;
import io.swagger.client.model.users.UsersInfo;
import io.swagger.client.model.widgets.UserWidget;
import io.swagger.client.model.widgets.UserWidgets;
import org.apache.commons.csv.CSVFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListWidgets {
    private static final String API_PATH = "api/rest/v6";
    private static final String API_URL = "https://api.adobesign.com/";
    private static final String API_USER_PREFIX = "email:";
    private static final String BEARER = "Bearer ";
    private static final int CAPACITY = 20000;
    private static final int PAGE_SIZE = 1000;
    private static final String SANDBOX = "--sandbox";
    private static final String SANDBOX_API_URL = "https://api.adobesignsandbox.com/";
    private static final int TIMEOUT = 300000;
    private static final String USAGE = "Usage: java -jar aas-list-templates-<version>.jar <integrationKey>";

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println(getUsage());
        }
        else {
            String accessToken = BEARER + args[0];
            ListWidgets list = new ListWidgets();
            try {
                if (args.length == 2 && args[1] != null && args[1].equalsIgnoreCase(SANDBOX)) {
                    list.execute(SANDBOX_API_URL, accessToken);
                }
                else {
                    list.execute(API_URL, accessToken);
                }
            }
            catch (ApiException ae) {
                System.out.println(getExceptionDetails(ae));
                ae.printStackTrace();
            }
        }
    }

    public void execute(String apiUrl, String accessToken) throws ApiException {
        /*
         *  Establish connection to Adobe Sign API, and obtain the correct API Access Point for the account
         */
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(apiUrl + API_PATH);
        apiClient.setConnectTimeout(TIMEOUT).setReadTimeout(TIMEOUT);
        BaseUrisApi baseUrisApi = new BaseUrisApi(apiClient);
        BaseUriInfo baseUriInfo = baseUrisApi.getBaseUris(accessToken);
        apiClient.setBasePath(baseUriInfo.getApiAccessPoint() + API_PATH);

        /*
         *  Instantiate APIs for account
         */
        GroupsApi groupsApi = new GroupsApi(apiClient);
        UsersApi usersApi = new UsersApi(apiClient);
        WidgetsApi widgetsApi = new WidgetsApi(apiClient);

        Map<String, String> foundGroups = new HashMap<>(CAPACITY);

        /*
         *  Populate the list of groups
         */
        GroupsInfo groupsInfo = groupsApi.getGroups(accessToken, null, null, PAGE_SIZE);
        List<GroupInfo> groupInfoList = groupsInfo.getGroupInfoList();
        while (groupInfoList != null && !groupInfoList.isEmpty()) {
            for (GroupInfo groupInfo: groupInfoList) {
                foundGroups.put(groupInfo.getGroupId(), groupInfo.getGroupName());
            }
            String groupCursor = groupsInfo.getPage().getNextCursor();
            if (groupCursor != null && !groupCursor.isEmpty()) {
                groupsInfo = groupsApi.getGroups(accessToken, null, groupCursor, PAGE_SIZE);
                groupInfoList = groupsInfo.getGroupInfoList();
            }
            else {
                groupInfoList = null;
            }
        }

        /*
         *  Obtain the first page of users
         */
        UsersInfo usersInfo = usersApi.getUsers(accessToken, null, null, PAGE_SIZE);
        List<UserInfo> userInfoList = usersInfo.getUserInfoList();
        System.out.println(format("web_form_id", "web_form_name", "owner_email","group_name"));
        while (userInfoList != null && !userInfoList.isEmpty()) {
            /*
             *  For each user:
             *  (a) Make sure that they are ACTIVE
             *  (b) Get the list of widgets they have access to
             *  (c) Output details
             */
            for (UserInfo userInfo: userInfoList) {
                DetailedUserInfo detail = usersApi.getUserDetail(accessToken, userInfo.getId(), null);
                if (detail != null && detail.getStatus().equals(DetailedUserInfo.StatusEnum.ACTIVE)) {
                    String email = userInfo.getEmail();
                    String apiUser = API_USER_PREFIX + email;
                    UserWidgets widgets = widgetsApi.getWidgets(accessToken,
                                                                apiUser,
                                                                null,
                                                                Boolean.FALSE,
                                                                null,
                                                                PAGE_SIZE);
                    List<UserWidget> widgetList = widgets.getUserWidgetList();
                    while (widgetList != null && !widgetList.isEmpty()) {
                        for (UserWidget widget : widgetList) {
                            System.out.println(format(widget.getId(),
                                                      widget.getName(),
                                                      email,
                                                      foundGroups.get(widget.getGroupId())));
                        }
                        String widgetCursor = widgets.getPage().getNextCursor();
                        if (widgetCursor != null && !widgetCursor.isEmpty()) {
                            widgets = widgetsApi.getWidgets(accessToken,
                                                            apiUser,
                                                            null,
                                                            Boolean.FALSE,
                                                            widgetCursor,
                                                            PAGE_SIZE);
                            widgetList = widgets.getUserWidgetList();
                        } else {
                            widgetList = null;
                        }
                    }
                }
            }
            String userCursor = usersInfo.getPage().getNextCursor();
            if (userCursor != null && !userCursor.isEmpty()) {
                usersInfo = usersApi.getUsers(accessToken, null, userCursor, PAGE_SIZE);
                userInfoList = usersInfo.getUserInfoList();
            }
            else {
                userInfoList = null;
            }
        }
    }

    private String format(String id, String name, String email, String groupName) {
        return CSVFormat.EXCEL.format(id, name, email, groupName);
    }

    private static String getExceptionDetails(ApiException e) {
        StringBuilder sb = new StringBuilder();
        if (e != null) {
            sb.append("Message: ");
            sb.append(e.getMessage());
            sb.append("\n");
            sb.append("Code: ");
            sb.append(e.getCode());
            sb.append("\n");
            sb.append("Response Headers: ");
            sb.append(e.getResponseHeaders());
            sb.append("\n");
            sb.append("Response Body: ");
            sb.append(e.getResponseBody());
        }
        return sb.toString();
    }

    private static String getUsage() {
        return USAGE;
    }
}
