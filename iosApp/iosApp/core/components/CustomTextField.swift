//
//  CustomTextField.swift
//  iosApp
//
//  Created by fturkan on 11.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CustomTextField: View {
  @Binding var text: String
  var charLimit: Int
  var isSecure: Bool = false
  @State private var isTapped = false
  var trailingIcon: String? = nil
  var labelText: String = ""
  var keyboardOptions: UIKeyboardType = .default
   
  var body: some View {
    VStack {
      VStack(alignment: .leading, spacing: 4) {
        HStack(spacing: 15) {
          if isSecure {
            SecureField(
              "",
              text: $text
            )
            .onChange(of: text) { newValue in
              if newValue.count > charLimit {
                text = String(newValue.prefix(charLimit))
              }
              withAnimation(.easeIn(duration: 0.08)) {
                isTapped = !newValue.isEmpty
              }
            }
            .keyboardType(keyboardOptions)
          } else {
            TextField(
              "",
              text: $text
            )
            .onChange(of: text) { newValue in
              if newValue.count > charLimit {
                text = String(newValue.prefix(charLimit))
              }
              withAnimation(.easeIn(duration: 0.08)) {
                isTapped = !newValue.isEmpty
              }
            }
            .keyboardType(keyboardOptions)
          }
           
          if let trailingIcon = trailingIcon {
            Button(
              action: {},
              label: {
                Image(systemName: trailingIcon)
                  .foregroundColor(.gray)
              }
            )
          }
        }
        .padding(.top, isTapped ? 15 : 0)
        .background(
          Text(labelText)
            .scaleEffect(isTapped ? 0.8 : 1)
            .offset(x: isTapped ? CGFloat( 4 - labelText.count) : 0, y: isTapped ? -15 : 0)
            .foregroundColor(.gray),
          alignment: .leading
        )
        .padding(.horizontal)
         
        Rectangle()
          .fill(isTapped ? Color.accentColor : Color.gray)
          .opacity(isTapped ? 1 : 0.5)
          .frame(height: 1)
          .padding(.top, 10)
      }
      .padding(.top, 12)
      .background(Color.gray.opacity(0.09))
      .cornerRadius(5)
    }
  }
}
