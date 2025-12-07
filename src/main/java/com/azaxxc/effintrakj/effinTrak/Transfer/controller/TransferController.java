package com.azaxxc.effintrakj.effinTrak.Transfer.controller;

import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.service.TransferService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public TransferController(TransferService transferService,
                             UserService userService,
                             GlobalResponseService globalResponseService) {
        this.transferService = transferService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Object> createTransfer(@Valid @RequestBody TransferRequestDTO dto) {
        Long userId = dto.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        TransferResponseDTO transfer = transferService.createTransfer(dto, user);
        return globalResponseService.success(transfer, "Transfer completed successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getTransfersByUserId(@PathVariable Long userId) {
        List<TransferResponseDTO> transfers = transferService.getTransfersByUserId(userId);
        return globalResponseService.success(transfers, "Fetched transfers for user");
    }
}

