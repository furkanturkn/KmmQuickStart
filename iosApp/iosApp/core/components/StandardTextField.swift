//
//  StandardTextField.swift
//  iosApp
//
//  Created by fturkan on 11.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Combine

struct StandardTextField: View {
    @Binding var text: String
    var hint: String
    var maxLength: Int = 400
    var error: String?
    var letterSpacing: CGFloat = 0.25
    var singleLine: Bool = true
    var maxLines: Int = 1
    var leadingIcon: Image?
    var keyboardType: UIKeyboardType = .default
    var isPasswordToggleDisplayed: Bool = false
    var onValueChange: (String) -> Void
    var imeAction: UIReturnKeyType = .next
    
    @State private var passwordVisible = false

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            TextField("test", text: $text)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding()
                .background(Color.surface)
                .cornerRadius(8)
                .overlay(
                    HStack {
                        if let leadingIcon = leadingIcon {
                            leadingIcon
                                .padding(.leading, 8)
                        }
                        Spacer()
                        if isPasswordToggleDisplayed {
                            Button(action: {
                                passwordVisible = !passwordVisible
                            }) {
                                Image(systemName: passwordVisible ? "eye.slash.fill" : "eye.fill")
                                    .foregroundColor(Color.primary)
                            }
                            .padding(.trailing, 18)
                        }
                    }
                        .foregroundColor(.clear)
                )
                .onAppear {
                    UITextField.appearance().backgroundColor = .clear
                }
                .onReceive(Just(text)) { newValue in
                    if newValue.count > maxLength {
                        text = String(newValue.prefix(maxLength))
                    }
                    onValueChange(text)
                }
                .font(Typography.displayLarge)
                .foregroundColor(.white)
            
            if let error = error, !error.isEmpty {
                Text(error)
                    .foregroundColor(Color.red)
                    .padding(.horizontal)
                    .padding(.bottom, 8)
            }
        }
        .frame(maxWidth: .infinity)
    }
}

struct StandardTextField_Preview: PreviewProvider {
    static var previews: some View {
        Group {
            StandardTextField(
                text: .constant(""),
                hint: "Username",
                onValueChange: { _ in },
                imeAction: .go
            )
            .padding()
            .previewLayout(.sizeThatFits)
            .background(Color(UIColor.systemBackground))
            .environment(\.colorScheme, .light)

            StandardTextField(
                text: .constant(""),
                hint: "Password",
                error: "Invalid password",
                onValueChange: { _ in },
                imeAction: .go
            )
            .padding()
            .previewLayout(.sizeThatFits)
            .background(Color(UIColor.systemBackground))
            .environment(\.colorScheme, .dark)
        }
    }
}
