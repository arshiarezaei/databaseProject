import java.util.Scanner;

public class Main {
    static boolean loggedIn = false;
    static String userPhoneNumber ;

    public static void main(String[] args) throws Exception{

        while (true){
            System.out.println("enter a command");
            Scanner input = new Scanner(System.in);
            String enteredCommand = input.nextLine();
            System.out.println("next line"+enteredCommand);
            if(enteredCommand.startsWith("login")) {
                String requestPhoneNumber=enteredCommand.substring(6,enteredCommand.length());
               // System.out.println(loggedIn);
                User loginRequest = new User(requestPhoneNumber);
                boolean resultOfLoginRequest=loginRequest.existsUser();
                if(resultOfLoginRequest==true){
                   String password = loginRequest.password();
                   if (password != null){
                       String enterredPassword;
                       do {
                           System.out.println("Enter password");
                           Scanner getPassword = new Scanner(System.in);
                            enterredPassword = getPassword.nextLine();
                       }while (!enterredPassword.equals(password));
                       System.out.println("you are logen in");
                   }
                    loggedIn = resultOfLoginRequest;
                   userPhoneNumber=requestPhoneNumber;

//                    loggedIn = resultOfLoginRequest;
//                    userPhoneNumber=requestPhoneNumber;
//                    //System.out.println(userPhoneNumber);
//                    //System.out.println(loggedIn);
                    }
                //System.out.println(loggedIn);
            }
            else if (enteredCommand.startsWith("logout") && loggedIn == true){
                loggedIn = false;
                userPhoneNumber="";

            }
            else if(enteredCommand.startsWith("set_bio") && loggedIn ==true){
                user_settings bio = new user_settings(userPhoneNumber,enteredCommand.substring(8,enteredCommand.length()));
                //System.out.println(userPhoneNumber);
                //System.out.println(enteredCommand.substring(8,enteredCommand.length()));
                bio.setBio();


            }
            else if (enteredCommand.startsWith("set_self_destruct")&&loggedIn==true){
                 char valueOFSelfDestruct = enteredCommand.charAt(18);
                 int s= Character.getNumericValue(valueOFSelfDestruct);
                 //System.out.println(s);
                if (s<=3 && s>=0) {
                    user_settings selfDestruct = new user_settings(userPhoneNumber);
                    selfDestruct.setSelfDestruct(s);
                }
                else {
                    System.out.println("not valid input");
                }

            }
            else if (enteredCommand.startsWith("set_password") && loggedIn==true){
                String pass = enteredCommand.substring(11,enteredCommand.length());
                //System.out.println(pass);
                User setPassword = new User(userPhoneNumber);
                setPassword.setPassword(pass);

            }
            else if(enteredCommand.startsWith("block_user") && loggedIn==true){
                String blockedNumber = enteredCommand.substring(13,enteredCommand.length());
                BlockedUser blockedUser = new BlockedUser();
                if (!userPhoneNumber.equals(blockedNumber)){
                    blockedUser.addBlockedUser(blockedNumber,userPhoneNumber);
                }
                else{
                    System.out.println("you cant block yourself");
                }

            }
            else if(enteredCommand.startsWith("unblock_user")){
                String unblockedUser = enteredCommand.substring(13,enteredCommand.length());
                //System.out.println(unblockedUser);
                BlockedUser unblockUser = new BlockedUser();
                unblockUser.unblockUser(unblockedUser,userPhoneNumber);


            }
            else if (enteredCommand.startsWith("create_user")){
                userPhoneNumber = enteredCommand.substring(12,enteredCommand.length());
                User user = new User(userPhoneNumber);
                user.createUser();
            }
            else if(enteredCommand.startsWith("set_name")&&loggedIn==true){
                String name = enteredCommand.substring(9,enteredCommand.length());
                User setName = new User(userPhoneNumber);
                setName.setName(name);
            }
            else if(enteredCommand.startsWith("send_message")&&loggedIn==true&&enteredCommand.length()==11){
                String receiver = enteredCommand.substring(13,24);
                String messageText = enteredCommand.substring(25,enteredCommand.length());
                System.out.println(receiver);
                System.out.println(messageText);
                PrivateChats message = new PrivateChats();
                message.sendMessage(userPhoneNumber,receiver,messageText);

            }
            else if(enteredCommand.startsWith("create_channel")&&loggedIn==true){
                String channelID = enteredCommand.substring(15,enteredCommand.lastIndexOf(" "));
                String channelName = enteredCommand.substring(enteredCommand.lastIndexOf(" ")+1 , enteredCommand.length());
                System.out.println(channelID);
                System.out.println(channelName);
                Channel newChannel = new Channel();
                newChannel.createChannel(channelID,channelName,userPhoneNumber);

            }
            else if(enteredCommand.startsWith("send_message_channel")&&loggedIn==true){
                String ID = Character.toString(enteredCommand.charAt(21));
                String messageText = enteredCommand.substring(23,enteredCommand.length());
                //System.out.println(enteredCommand.indexOf(" "));
                Channel newChannel = new Channel();
                boolean isAdmin =  newChannel.isAdmin(userPhoneNumber,ID);
                if (isAdmin){
                    //System.out.println("if ");
                    ChannelMessage cm = new ChannelMessage();
                    cm.sendMessage(ID,messageText);

                }

            }
            else if (enteredCommand.startsWith("create_group")&&loggedIn==true){
                String arg = enteredCommand.substring(enteredCommand.indexOf(" ")+1,enteredCommand.length());
                System.out.println(arg);
                String groupID = enteredCommand.substring(0,arg.indexOf(" "));
                String groupName =enteredCommand.substring(" ",arg.length());
                Groupes newGrou

            }

        }

    }

}
