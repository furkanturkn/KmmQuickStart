import SwiftUI
import Shared

struct ContentView: View {
    private let appModule = AppModule()
    
    @ObservedObject var router = Router()
    
    var body: some View {
        NavigationStack(path: $router.navPath) {
            SplashScreen(
                sharedPref: appModule.sharedPref
            )
            .navigationDestination(for: Router.Destination.self) { destination in
                switch destination {
                case .login:
                    LoginScreen(
                        validatePhoneNumberUseCase: appModule.validatePhoneNumberUseCase
                    ).navigationBarBackButtonHidden(true)
                case .home:
                    SplashScreen(
                        sharedPref: appModule.sharedPref
                    )
                }
                
                
            }
        }.environmentObject(router)
        
    }
}
