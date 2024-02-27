//
//  IosLoginViewModel.swift
//  iosApp
//
//  Created by fturkan on 11.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

extension LoginScreen {
    @MainActor class IosLoginViewModel: ObservableObject {
     
        private let viewModel: LoginViewModel
        private var validatePhoneNumberUseCase: ValidatePhoneNumberUseCase

      
        @Published var state: LoginContractState = LoginContractState(
            countries: [],
            showOtpBottomSheet: false,
            phoneNumber: "",
            phoneNumberError: AuthError.FieldBlank(),
            selectedPhoneNumberCountry: CountryKt.countryTurkey,
            isCountrySelectionSheetVisible: false,
            currentRoute: nil,
            isLoadingShowing: false
        )
        private var handle: DisposableHandle?

        init(
            validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
        ) {
            self.validatePhoneNumberUseCase = validatePhoneNumberUseCase
            self.viewModel = LoginViewModel(
                validatePhoneNumberUseCase: validatePhoneNumberUseCase,
                coroutineScope: nil
            )
        }

        func onEvent(event: LoginContractEvent) {
            self.viewModel.onEvent(event: event)
        }

        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }

        func dispose() {
            handle?.dispose()
        }
         
    }
}

