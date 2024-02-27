//
//  LoginScreen.swift
//  iosApp
//
//  Created by fturkan on 11.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct LoginScreen: View {
    
    private var validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
    
    @ObservedObject var viewModel: IosLoginViewModel
    
    
    init(
        validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
    ) {
        self.validatePhoneNumberUseCase = validatePhoneNumberUseCase
        
        
        self.viewModel = IosLoginViewModel(
            validatePhoneNumberUseCase: validatePhoneNumberUseCase
        )
    }
    
    
    var body: some View {
        VStack(spacing: 0) {
            TopSection()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Color.primary)
                .frame(height: UIScreen.main.bounds.height * 0.3)
            
            Spacer().frame(height: 16)
            
            VStack() {
                
                PhoneNumberSection(
                    phoneNumber: Binding(
                        get: { viewModel.state.phoneNumber },
                        set: { newPhoneNumberValue in
                            viewModel.onEvent(event: LoginContractEvent.PhoneNumberUpdated(value: newPhoneNumberValue))
                        }),
                    selectedPhoneNumberCountry: viewModel.state.selectedPhoneNumberCountry,
                    onWelcomeContractEvent: { viewModel.onEvent(event: $0) }
                )
                
                
                
                Spacer().frame(height: 16)
                
                /*
                 NavigationLink(
                 destination: HomeScreen().navigationBarBackButtonHidden(true),
                 isActive: Binding(
                 get: { viewModel.state.currentRoute == "home" },
                 set: { _ in
                 
                 }
                 
                 )
                 ) {
                 
                 StandardButton(
                 onClick: {
                 viewModel.onEvent(event: LoginContractEvent.LoginClicked())
                 },
                 text: "Login")
                 
                 }
                 
                 */
                Spacer().frame(height: 16)
                
                
                /*
                 NavigationLink(
                 destination: OtpScreen(validatePhoneNumberUseCase: validatePhoneNumberUseCase)
                 ) {
                 
                 Text("Get password/ renew")
                 .foregroundColor(Color.primary)
                 .font(.headline)
                 
                 }
                 
                 */
                
                Spacer()
                
                Image(resource: \.test)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 128, height: 128)
            }
            .padding(16)
            .sheet(
                isPresented: Binding(
                    get: { viewModel.state.isCountrySelectionSheetVisible },
                    set: { value in if(value == false) {viewModel.onEvent(event: LoginContractEvent.DismissCountrySelectionSheet())} }
                ),
                content: {
                    CountrySelectionSheetContent(
                        countries: [CountryKt.countryTurkey, CountryKt.countryUSA]
                    ){ phoneNumberCountry in
                        viewModel.onEvent(event: LoginContractEvent.UpdatePhoneNumberCountry(country: phoneNumberCountry))
                        viewModel.onEvent(event: LoginContractEvent.DismissCountrySelectionSheet())
                    }
                })
            .background(Color.background)
            .edgesIgnoringSafeArea(.all)
        }
        
        
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        
    }
    
}

struct TopSection: View {
    var body: some View {
        VStack {
            Spacer()
            
            HStack {
                VStack(alignment: .leading) {
                    Text("Bau Pass")
                        .foregroundColor(Color.background)
                        .font(.largeTitle)
                        .padding(.bottom, 8)
                    
                    Text("New generation access control")
                        .foregroundColor(Color.background)
                        .font(.headline)
                }
                Spacer()
            }
            .padding(16)
            .background(Color.primary)
            .edgesIgnoringSafeArea(.all)
        }
    }
}

struct PhoneNumberSection: View {
    
    @Binding var phoneNumber: String
    let selectedPhoneNumberCountry: Country
    
    let onWelcomeContractEvent: (LoginContractEvent) -> Void
    
    var body: some View {
        HStack {
            CountrySelection(selectedCountry: selectedPhoneNumberCountry) {
                onWelcomeContractEvent(LoginContractEvent.ShowCountrySelectionSheet())
            }
            
            Spacer()
            
            CustomTextField(
                text: $phoneNumber,
                charLimit: 30,
                labelText: Strings().get(
                    id: SharedRes.strings().phone_number_or_email_hint,
                    args: []
                ),
                keyboardOptions: .emailAddress
            )
        }
    }
}
